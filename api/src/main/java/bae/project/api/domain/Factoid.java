package bae.project.api.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Factoid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "axiom", nullable = false)
    private boolean axiom;

    @Column(name = "explanation",nullable = false)
    private String explanation;

    //TODO:img and tooltip, user parent id

    public Factoid() {
    }

    public Factoid(String content, boolean axiom, String explanation) {
        this.content = content;
        this.axiom = axiom;
        this.explanation = explanation;
    }

    public Factoid(Long id, String content, boolean axiom, String explanation) {
        this.id = id;
        this.content = content;
        this.axiom = axiom;
        this.explanation = explanation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAxiom() {
        return axiom;
    }

    public void setAxiom(boolean axiom) {
        this.axiom = axiom;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factoid factoid = (Factoid) o;
        return axiom == factoid.axiom && Objects.equals(id, factoid.id) && Objects.equals(content, factoid.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, axiom);
    }

    @Override
    public String toString() {
        return "Factoid{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", axiom=" + axiom +
                '}';
    }
}
