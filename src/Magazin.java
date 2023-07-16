
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Magazin {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String name;

	@ManyToMany(mappedBy = "magazin")
	private List<Article> article = new ArrayList<>();

	@OneToMany(mappedBy = "magazin")
	private List<Pret> preturi = new ArrayList<>();

	public Magazin() {

	}

	public Magazin(String name) {
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

	public List<Article> getArticles() {
		return article;
	}

	public void setArticles(List<Article> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Magazin [id=" + id + ", name=" + name + ", article=" + article + "]";
	}

	public List<Pret> getPrets() {
		return preturi;
	}

	public void setPrets(List<Pret> preturi) {
		this.preturi = preturi;
	}

}
