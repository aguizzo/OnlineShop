package onlineshop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import onlineshop.dao.RoleDao;
import onlineshop.dao.UserDao;
import onlineshop.dto.UserDto;
import onlineshop.utils.db.DBUtils;

public class MySqlJdbcUserDao implements UserDao {
	// Table column names
	private static String ID = "id";
	private static String FIRST_NAME = "first_name";
	private static String LAST_NAME = "last_name";
	private static String EMAIL = "email";
	private static String PASSWORD = "password";
	private static String ROLE = "fk_user_role";
	private static String MONEY = "money";
	private static String CREDIT_CARD = "credit_card";

	private RoleDao roleDao;

	public MySqlJdbcUserDao() {
		roleDao = new MySqlJdbcRoleDao();
	}

	@Override
	public boolean saveUser(UserDto user) {
		String query = "INSERT INTO user (" + FIRST_NAME + "," + LAST_NAME + "," + EMAIL + "," + ROLE + "," + MONEY
				+ "," + CREDIT_CARD + "," + PASSWORD + ") " + "VALUES (?, ?, ?, ?, ?, ?, ?);";
		try (var connection = DBUtils.getConnection();
				var ps = connection.prepareStatement(query);) {
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			if (user.getRoleDto() != null && user.getRoleDto().getId() != null)
				ps.setInt(4, user.getRoleDto().getId());
			else
				ps.setNull(4, java.sql.Types.NULL);
			ps.setBigDecimal(5, user.getMoney());
			ps.setString(6, user.getCreditCard());
			ps.setString(7, user.getPassword());
			
			int result = ps.executeUpdate();
			return result != 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<UserDto> getUsers() {
		String query = "SELECT * FROM user";
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {

			try (var rs = ps.executeQuery()) {
				List<UserDto> users = new ArrayList<UserDto>();
				while (rs.next())
					users.add(getUserDto(rs));
				return users;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserByEmail(String userEmail) {
		String query = "SELECT * FROM user WHERE email = ?";
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {
			ps.setString(1, userEmail);

			try (var rs = ps.executeQuery()) {
				if (rs.next())
					return getUserDto(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserDto getUserById(int id) {
		String query = "SELECT * FROM user WHERE id = ?";
		try (var connection = DBUtils.getConnection(); var ps = connection.prepareStatement(query);) {
			ps.setInt(1, id);

			try (var rs = ps.executeQuery()) {
				if (rs.next())
					return getUserDto(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserDto getUserDto(ResultSet rs) throws SQLException {
		var userDto = new UserDto();
		
		userDto.setId(rs.getInt(ID));
		userDto.setFirstName(rs.getString(FIRST_NAME));
		userDto.setLastName(rs.getString(LAST_NAME));
		userDto.setEmail(rs.getString(EMAIL));
		userDto.setPassword(rs.getString(PASSWORD));
		userDto.setRoleDto(roleDao.getRoleById(rs.getInt(ROLE)));
		userDto.setMoney(rs.getBigDecimal(MONEY));

		return userDto;
	}

}
