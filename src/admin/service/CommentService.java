package admin.service;

import admin.dao.CommentDAO;
import admin.model.Comment;

import java.util.List;

public class CommentService {
    private CommentDAO commentDAO = new CommentDAO();
    public List<Comment> getAll(Comment comment){
        return commentDAO.getAll(comment);
    }
    public Boolean add(Comment comment){return commentDAO.add(comment);}
}
