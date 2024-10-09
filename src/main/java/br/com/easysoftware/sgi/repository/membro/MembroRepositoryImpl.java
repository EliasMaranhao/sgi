package br.com.easysoftware.sgi.repository.membro;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import br.com.easysoftware.sgi.entity.Membro;
import br.com.easysoftware.sgi.repository.filter.MembroFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class MembroRepositoryImpl implements MembroRepositoryQuery{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Membro> filtrar(MembroFilter membroFilter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Membro> criteria = builder.createQuery(Membro.class);

        Root<Membro> root = criteria.from(Membro.class);

        //restricoes
        Predicate[] predicates = criarRestricoes(membroFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Membro> query = entityManager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(membroFilter));
    }

    private Long total(MembroFilter membroFilter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

        Root<Membro> root = criteria.from(Membro.class);

        Predicate[] predicates = criarRestricoes(membroFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Membro> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Predicate[] criarRestricoes(MembroFilter membroFilter,  CriteriaBuilder builder, Root<Membro> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(membroFilter.getNome())){
            predicates.add(builder.like(builder.lower(root.get("nome")), "%" + membroFilter.getNome().toLowerCase() + "%"));
        }

        if(membroFilter.getDataConversaoDe() != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataConversao"), membroFilter.getDataConversaoDe()));
        }

        if(membroFilter.getDataConversaoDeAte() != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("dataConversao"), membroFilter.getDataConversaoDeAte()));
        }

        if(membroFilter.getDataBatismoDe() != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataBatismo"), membroFilter.getDataBatismoDe()));
        }

        if(membroFilter.getDataBatismoDeAte() != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("dataBatismo"), membroFilter.getDataBatismoDeAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
    
}
