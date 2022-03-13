package com.spot.mvc.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table (name="CLIENTI")
@NamedQuery(name = "UserDTO.searchUserByEmail", query = "SELECT p FROM UserDTO p WHERE LOWER(p.email) = LOWER(?1)")

public class UserDTO {
	
	/**/
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;
    
    //@NotEmpty(message = "error.name.empty")
    @Column(name = "NAME")
    @Length(max = 50, message = "error.name.length")
    private String name;

    @Length(max = 150, message = "error.address.length")
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "PIVA")
    @Length(max = 11, message = "error.vat.length")
	private String piva;

    @Column(name = "CFISC")
	private String cfisc;
    
	@Email(message = "error.email.email")
    @Length(max = 80, message = "error.email.length")
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TEL")
	private String tel;

    @Column(name = "TIPO")
	private String tipo;
    
    @Column(name = "PASSWORD")
	private String password;
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

    public String getCfisc() {
		return cfisc;
	}

	public void setCfisc(String cfisc) {
		this.cfisc = cfisc;
	}

    public String getPiva() {
		return piva;
	}
	public void setPiva(String piva) {
		this.piva = piva;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
