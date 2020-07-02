package kagoyume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;

public class UserDataDAO {

	public static UserDataDAO getInstance() {
		return new UserDataDAO();
	}

//	ユーザーアカウント登録
	public void makeAcount(UserDataDTO dto) throws SQLException{
		Connection	con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("INSERT INTO user_t (name,password,mail,address,newDate)values(?,?,?,?,?);");
			ps.setString(1,dto.getName());
			ps.setString(2, dto.getPassword());
			ps.setString(3,dto.getMail());
			ps.setString(4, dto.getAddress());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
	        System.out.println("insert completed");
		}catch(SQLException e){
			System.out.print(e.getMessage());
			throw new SQLException();
		}catch(NullPointerException ne) {
			System.out.print(ne.getMessage());
			throw new SQLException();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}

//	ログイン処理
	public int loginCheck(UserData ud) throws SQLException{
		Connection	con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("SELECT * FROM user_t WHERE name=? AND password=?;");
			ps.setString(1, ud.getName());
			ps.setString(2,ud.getPassword());
			rs = ps.executeQuery();
				while(rs.next()) {
					UserDataDTO uddto = new UserDataDTO();
					uddto.setName(rs.getString("name"));
					uddto.setPassword(rs.getString("password"));
//					ユーザーネームとパスワードが合っていればログイン成功として１を返す
//					削除されたユーザーの場合２を返す
					if(rs.getInt("deleteFlg")==1) {
						return 2;
					}
					if(uddto.getName().equals(ud.getName())&&uddto.getPassword().equals(ud.getPassword())) {
						return 1;
					}

				}
//				ユーザーネームとパスワードが間違っていれば２を返す
				return 0;
			}catch(SQLException e) {
				System.out.print(e.getMessage());
				return 0;
			}catch(NullPointerException ne) {
				System.out.print(ne.getMessage());
				throw new SQLException();
			}finally {
				if(con!=null) {
					con.close();
				}

			}
	}
//	登録されたユーザー情報をユーザー名で検索して返す
	public UserDataDTO getUserData(UserData ud) throws SQLException{
		Connection	con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			UserDataDTO uddto = new UserDataDTO();
			con = DBManager.getConnection();
			ps = con.prepareStatement("SELECT * FROM user_t WHERE name=?;");
			ps.setString(1,ud.getName());
			rs = ps.executeQuery();
				while(rs.next()) {
					uddto.setUserID(rs.getInt("userID"));
					uddto.setName(rs.getString("name"));
					uddto.setPassword(rs.getString("password"));
					uddto.setMail(rs.getString("mail"));
					uddto.setAddress(rs.getString("address"));
					uddto.setTotal(rs.getInt("total"));
					uddto.setNewDate(rs.getTimestamp("newDate"));
					return uddto;
				}
				return uddto;
			}catch(SQLException e) {
				System.out.print(e.getMessage());
				throw new SQLException();
			}catch(NullPointerException ne) {
				System.out.print(ne.getMessage());
				throw new SQLException();
			}finally {
				if(con!=null) {
					con.close();
				}
			}
	}


//	購入履歴をyahooAPIから呼び出すためにuserIDに紐付いたitemCodeとtypeを配列で呼び出す
	public List<UserDataDTO> purchaseHistory(UserDataDTO uddto) throws SQLException{
		List<UserDataDTO> itemCodeList = new ArrayList<UserDataDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("SELECT * FROM buy_t WHERE userID = ?;");
			ps.setInt(1, uddto.getUserID());
			rs = ps.executeQuery();
			while(rs.next()) {
				UserDataDTO dto = new UserDataDTO();
				dto.setItemCode(rs.getString("itemCode"));
				dto.setType(rs.getInt("type"));
				itemCodeList.add(dto);
			}
			return itemCodeList;
		}catch(SQLException e) {
			System.out.print(e.getMessage());
			throw new SQLException();
		}catch(NullPointerException ne) {
			System.out.print(ne.getMessage());
			throw new SQLException();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}
//ユーザーアカウントの更新
	public void updateUser(UserDataDTO uddto) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("UPDATE user_t SET name=?, password=?, mail=?, address=?, newDate=? WHERE userID = ?;");
			ps.setString(1, uddto.getName());
			ps.setString(2,uddto.getPassword());
			ps.setString(3, uddto.getMail());
			ps.setString(4,uddto.getAddress());
			ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
			ps.setInt(6, uddto.getUserID());
			ps.executeUpdate();
			System.out.println("update success!!");
		}catch(SQLException e) {
			System.out.print(e.getMessage());
			throw new SQLException();
		}catch(NullPointerException ne) {
			System.out.print(ne.getMessage());
			throw new SQLException();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}
//	購入処理
	public void buy(List<UserDataDTO> uddtolist,UserDataDTO uddto) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("INSERT INTO buy_t (userID,itemCode,type,buyDate)VALUES(?,?,?,?);");
			ps2 = con.prepareStatement("UPDATE user_t set total=total+? where userID=?; ");
//			カートの中身をリストを回してbuy_tへ挿入
			for(UserDataDTO e : uddtolist) {
			ps.setInt(1, e.getUserID());
			ps.setString(2, e.getItemCode());
			ps.setInt(3, e.getType());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
			System.out.print("購入完了");
			}
//			今回の買い物にかかった合計金額をuser_tへ挿入
			ps2.setInt(1,uddto.getTotal());
			ps2.setInt(2, uddto.getUserID());
			ps2.executeUpdate();
			System.out.println("更新完了");
		}catch(SQLException e) {
			System.out.print(e.getMessage());
			throw new SQLException();
		}catch(NullPointerException ne) {
			System.out.print(ne.getMessage());
			throw new SQLException();
		}finally {
			if(con!=null) {
			con.close();
			}
		}
	}

	public void delete(UserDataDTO uddto) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBManager.getConnection();
			ps = con.prepareStatement("UPDATE user_t SET deleteFlg = ? WHERE userID = ?;");
			ps.setInt(1, 1);
			ps.setInt(2, uddto.getUserID());
			ps.executeUpdate();
			System.out.print("delete complete");
		}catch(SQLException e) {
			System.out.print(e.getMessage());
			throw new SQLException();
		}catch(NullPointerException ne) {
			System.out.print(ne.getMessage());
			throw new SQLException();
		}finally {
			if(con!=null) {
			con.close();
			}
		}
	}
}
