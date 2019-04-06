package admin.dao;

import admin.model.Comment;
import admin.model.Customer;
import admin.model.Product;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public List<Comment> getAll(Comment comment){
        List<Comment> list = new ArrayList<Comment>();
        String sql = "SELECT c.`comment`, c.create_at, cu.`name`, c.star_vote FROM `comment` c, customer cu WHERE c.customer_id = cu.id AND c.product_id = ? ORDER BY c.create_at";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, comment.getProduct().getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Comment comment1 = new Comment();
                comment1.setCreateAt(rs.getTimestamp("create_at"));
                Customer c = new Customer();
                c.setName(rs.getString("name"));
                comment1.setCustomer(c);
                comment1.setComment(rs.getString("comment"));
                comment1.setStarVote(rs.getInt("star_vote"));
                list.add(comment1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public Boolean add(Comment comment){
        String sql = "insert into comment(product_id, customer_id, comment, create_at, star_vote) values (?,?,?,?,?)";
        try {
            Connection connection = ConnectionUtil.open();
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(1, comment.getProduct().getId());
            statement.setInt(2, comment.getCustomer().getId());
            statement.setString(3, comment.getComment());
            statement.setTimestamp(4, comment.getCreateAt());
            statement.setInt(5, comment.getStarVote());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
