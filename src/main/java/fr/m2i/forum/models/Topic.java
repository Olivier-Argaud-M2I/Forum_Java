package fr.m2i.forum.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;



@Entity
@Table(name="topic")
@NamedQueries({
        @NamedQuery(name="selectAllTopic", query="SELECT topic FROM Topic topic"),
        @NamedQuery(name="findTopicById", query="SELECT topic FROM Topic topic WHERE id = :id"),
        @NamedQuery(name="findTopicByTitle", query="SELECT topic FROM Topic topic WHERE topic.title = :title"),
        @NamedQuery(name="deleteTopicById",query ="DELETE FROM Topic WHERE id = :id")

})
public class Topic {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Basic
    @Column(name = "timestamp")
    private Long timestamp;

    @OneToMany(
            targetEntity = TopicResponse.class,
            mappedBy="topic",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<TopicResponse> responses;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<TopicResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<TopicResponse> responses) {
        this.responses = responses;
    }

    public Topic() {
    }

    public Topic(String title, String text, User author, Long timestamp) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.timestamp = timestamp;
    }

    public String dateToString(){
        DateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return format.format(Date.from(Instant.ofEpochMilli(timestamp)));
    }
}
