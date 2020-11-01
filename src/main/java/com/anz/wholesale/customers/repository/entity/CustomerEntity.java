package com.anz.wholesale.customers.repository.entity;

import com.anz.wholesale.accounts.repository.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    //storing the password which is used to get the user token to access accounts
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SCOPES")
    private String scopes;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<AccountEntity> accounts;
}
