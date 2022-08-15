package fr.m2i.forum.cruds;

import fr.m2i.forum.models.Topic;
import fr.m2i.forum.models.TopicResponse;
import fr.m2i.forum.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TopicCrud {

    private EntityManagerFactory factory;

    public TopicCrud(){
        this.factory = Persistence.createEntityManagerFactory("ForumBdd");
    }

    public List<Topic> getTopics(){
        EntityManager em = factory.createEntityManager();
        List<Topic> topics = em.createNamedQuery("selectAllTopic").getResultList();
        em.close();
        return topics;
    }

    public Topic getTopicById(Integer id){
        EntityManager em = factory.createEntityManager();
        Topic topic = (Topic)em.createNamedQuery("findTopicById")
                .setParameter("id",id)
                .getSingleResult();
        em.close();
        return topic;
    }

    public TopicResponse getTopicResponseById(Integer id){
        EntityManager em = factory.createEntityManager();
        TopicResponse topicResponse = (TopicResponse)em.createNamedQuery("findTopicResponseById")
                .setParameter("id",id)
                .getSingleResult();

        em.close();
        return topicResponse;
    }

    public Topic getTopicByTitle(String title){
        EntityManager em = factory.createEntityManager();
        Topic topic = (Topic)em.createNamedQuery("findTopicByTitle")
                .setParameter("title",title)
                .getSingleResult();
        em.close();
        return topic;
    }

    public Topic saveTopic(Topic topic){
        EntityManager em = factory.createEntityManager();
        if(topic.getId()==null){
            em.getTransaction().begin();
            boolean valid = false;
            try{
                em.persist(topic);
                valid = true;
            }
            finally {
                if (valid){
                    em.getTransaction().commit();
                }
                else{
                    em.getTransaction().rollback();
                }
            }
            em.refresh(topic);
        }
        else{
            Topic topic1 = em.find(Topic.class,topic.getId());
            topic = em.merge(topic1);
        }
        em.close();
        return topic;
    }

    public TopicResponse saveTopicResponse(TopicResponse topicResponse){
        EntityManager em = factory.createEntityManager();
        if(topicResponse.getId()==null){
            em.getTransaction().begin();
            boolean valid = false;
            try{
                em.persist(topicResponse);
                valid = true;
            }
            finally {
                if (valid){
                    em.getTransaction().commit();
                }
                else{
                    em.getTransaction().rollback();
                }
            }
            em.refresh(topicResponse);
        }
        else{
            TopicResponse topicResponse1 = em.find(TopicResponse.class,topicResponse.getId());
            topicResponse = em.merge(topicResponse1);
        }
        em.close();
        return topicResponse;
    }


    public void deleteTopic(Topic topic){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        boolean valid = false;
        try{
            em.remove(topic);
            valid = true;
        }
        finally {
            if (valid){
                em.getTransaction().commit();
            }
            else{
                em.getTransaction().rollback();
            }
        }
        em.close();
    }

    public void deleteTopicById(Integer id){
        EntityManager em = factory.createEntityManager();
        Topic topic = em.find(Topic.class,id);
        em.getTransaction().begin();
        boolean valid = false;
        try{
            em.remove(topic);
            valid = true;
        }
        finally {
            if (valid){
                em.getTransaction().commit();
            }
            else{
                em.getTransaction().rollback();
            }
        }
        em.close();
    }

    public void deleteTopicResponseById(Integer id){
        EntityManager em = factory.createEntityManager();
        TopicResponse topicResponse = em.find(TopicResponse.class,id);
        em.getTransaction().begin();
        boolean valid = false;
        try{
//            em.remove(topicResponse);
            em.createNamedQuery("deleteTopicResponseById")
                    .setParameter("id",id)
                    .executeUpdate();
            valid = true;
        }
        finally {
            if (valid){
                em.getTransaction().commit();
            }
            else{
                em.getTransaction().rollback();
            }
        }
        em.close();
    }



}
