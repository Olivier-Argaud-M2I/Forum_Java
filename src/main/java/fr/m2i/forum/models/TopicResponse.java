package fr.m2i.forum.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;


@Entity
@Table(name="topic_response")
@NamedQueries({

        @NamedQuery(name="findTopicResponseById", query="SELECT topicReponse FROM TopicResponse topicReponse WHERE id = :id"),
        @NamedQuery(name="deleteTopicResponseById",query ="DELETE FROM TopicResponse WHERE id = :id")
})
@NamedNativeQueries({
        @NamedNativeQuery(name="selectAllTopicResponseByTopicTitle", query="SELECT * FROM topic_response JOIN topic ON topic.id = topic_response.topic_id where topic.title = :title"),
        @NamedNativeQuery(name="deleteTopicReposnseById2", query="DELETE FROM topic_response WHERE topic_response.id = :id"),
        @NamedNativeQuery(name="findTopicResponseById2", query="SELECT * FROM topic_response WHERE topic_response.id = :id")
})
public class TopicResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Basic
    @Column(name = "timestamp")
    private Long timestamp;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public TopicResponse() {
    }

    public TopicResponse(String text, User author, Long timestamp, Topic topic) {
        this.text = text;
        this.author = author;
        this.timestamp = timestamp;
        this.topic = topic;
    }

    public String dateToString(){
        DateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        return format.format(Date.from(Instant.ofEpochMilli(timestamp)));
    }

}
