package com.saternos.bookshop.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson(deepSerialize = true)
public class Book {

    /**
     */
    @NotNull
    @Size(min = 2)
    private String name;

    /**
     */
    private BigDecimal price;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<Author>();
}
