package com.mhts.hibernate.query;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name="t_customer")
@NamedQuery(name="listCustomer", query="from Customer")
@SqlResultSetMapping(name="customerSetMapping",entities={
		@EntityResult(entityClass=Customer.class, fields={ 
				@FieldResult(name="id",column="id"), @FieldResult(name="name",column="name") }) })
@NamedNativeQuery(name="listCustomerSql", query="SELECT * FROM t_customer", resultSetMapping="customerSetMapping")
@Proxy(lazy=true)
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String name;

    // �����ͻ������ж������
    /*
     * targetEntity="..."���൱��<one-to-many class="...">
     * mappedBy="..."���൱��inverse=true��������������ϵ��ά������Ȼ������һ���м��
     */
    @OneToMany(targetEntity=Order.class, mappedBy="c")
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.TRUE)
//    ����ÿ������ץȡ������
    @BatchSize(size=10)
    private Set<Order> orders = new HashSet<Order>();

    public Customer() {
    	
    }
    
    public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    
	public Set<Order> getOrders() {
        return orders;
    }
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}

}
