package onlineshop.dao.impl;

import java.sql.SQLException;

import onlineshop.dao.RoleDao;
import onlineshop.dto.RoleDto;
import onlineshop.utils.db.DBUtils;

public class MySqlJdbcRoleDao implements RoleDao {

	@Override
	public RoleDto getRoleById(int id) {
		String query = "SELECT * FROM role WHERE id = ?";

		try (var connection = DBUtils.getConnection();
				var ps = connection.prepareStatement(query);) {
			ps.setInt(1, id);

			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					RoleDto role = new RoleDto();
					role.setId(rs.getInt("id"));
					role.setRoleName(rs.getString("role_name"));
					return role;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
