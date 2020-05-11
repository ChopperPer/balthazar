package se.rsv.ocp.balthazar.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Version
    @Column(columnDefinition = "integer default 1")
    private Integer jpa_version = Integer.valueOf(1);

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getJpa_version() {
        return jpa_version;
    }

    public void setJpa_version(Integer jpa_version) {
        this.jpa_version = jpa_version;
    }
}
