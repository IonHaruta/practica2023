
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Pret implements Serializable{
//	@Id
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
//	private long id;
	private int value;

	@Id
	@ManyToOne
	@JoinColumn(name = "Magazin_id", nullable = false)
	private Magazin magazin;

	@Id
	@ManyToOne
	@JoinColumn(name = "Article_id", nullable = false)
	private Article article;

	public Pret(int value, Magazin magazin, Article article) {
		super();
		this.value = value;
		this.magazin = magazin;
		this.article = article;
	}

	public Pret() {
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Magazin getMagazin() {
		return magazin;
	}

	public void setMagazin(Magazin magazin) {
		this.magazin = magazin;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

}
