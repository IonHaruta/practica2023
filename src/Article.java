

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Article {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String name;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Article_Magazin", //
			joinColumns = { @JoinColumn(name = "article_id") }, //
			inverseJoinColumns = { @JoinColumn(name = "magazin_id") })
	private List<Magazin> magazin = new ArrayList<>();

	@OneToMany(mappedBy = "article")
	private List<Pret> preturi = new ArrayList<>();

	public Article() {

	}

	public Article(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Magazin> getMagazin() {
		return magazin;
	}

	public void setMagazin(List<Magazin> magazin) {
		this.magazin = magazin;
	}

	public List<Pret> getPret() {
		return preturi;
	}

	public void setPret(List<Pret> preturi) {
		this.preturi = preturi;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + "]";
	}

}
