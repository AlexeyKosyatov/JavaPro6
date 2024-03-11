package ru.stepup.javapro.javapro5.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stepup.javapro.javapro5.entity.ProductEntity;
import ru.stepup.javapro.javapro5.repository.HikaryConnectionPool;
import ru.stepup.javapro.javapro5.repository.ProductDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(HikaryConnectionPool hikaryConnectionPool) {
        this.dataSource = hikaryConnectionPool.dataSource();
    }

    @Override
    public void create(ProductEntity productEntity) {
        String sql = "insert into product (id, account_number, balance, type, user_id) values(?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, productEntity.getId());
            ps.setString(2, productEntity.getAccountNumber());
            ps.setBigDecimal(3, productEntity.getBalance());
            ps.setInt(4, productEntity.getType());
            ps.setLong(5, productEntity.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "delete from product";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductEntity> selectAllByUserId(Long userId) {
        String sql = "select * from product where user_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, userId);
            List<ProductEntity> productEntities = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var product = new ProductEntity(
                        rs.getLong("id"),
                        rs.getString("account_number"),
                        rs.getBigDecimal("balance"),
                        rs.getInt("type"),
                        rs.getLong("user_id")
                );
                productEntities.add(product);
            }
            return productEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductEntity selectById(Long id) {
        String sql = "select * from product where id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ProductEntity(
                        rs.getLong("id"),
                        rs.getString("account_number"),
                        rs.getBigDecimal("balance"),
                        rs.getInt("type"),
                        rs.getLong("user_id")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from product where Id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}