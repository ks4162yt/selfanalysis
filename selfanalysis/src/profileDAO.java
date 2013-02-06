import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.mysql.jdbc.Statement;

public class profileDAO{

    //データベースとの接続に必要な情報を保持する定数
	private final static String DRIVER_URL = "jdbc:mysql://localhost:3306/selfanalysis?useUnicode=true&characterEncoding=UTF-8";
	private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final static String USER_NAME = "yamanaka";
	private final static String PASSWORD = "Vq9CZFTX";

	// 接続メソッド
	public Connection createConnection() {
		try {
			Class.forName(DRIVER_NAME);
			//System.out.println("before connect");
			Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME,
					PASSWORD);
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println("Can't find jdbc driver");
		} catch (SQLException e) {
			System.out.println("Connect Errorですよ");
		}
		return null; // 例外が発生した場合はnullを返す
	}

	// 切断メソッド
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}



	public ArrayList<profileTransferObject> getYourProfile(String yourname){

		Connection con = createConnection();
		ResultSet result = null;
		StringBuffer returnSB = null;
		ArrayList<profileTransferObject> transferObjList = new ArrayList<>();

		try{
			Statement select = (Statement) con.createStatement();
			String sql = "SELECT * FROM personalInfo WHERE id = (select id from nameid where name = '" + yourname + "')";
			result = select.executeQuery(sql);
			returnSB = new StringBuffer();
			while(result.next()){
				String name = result.getString("name");
				int appearance = result.getInt("appearance");
				int chara = result.getInt("chara");
				int age = result.getInt("age");
				int income = result.getInt("income");
				int status = result.getInt("status");
				int total = result.getInt("total");
				profileTransferObject obj = new profileTransferObject(name, appearance, chara, age, income, status, total);
				transferObjList.add(obj);
				System.out.println("name = " + name + "total = " + total);
				//returnSB.append(result.getString("name") + ',');
				//System.out.println(result.getString("name"));
			}
			//returnSB.deleteCharAt(returnSB.length() - 1);

		}catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}

		//return returnSB.toString();
		return transferObjList;
	}

	/*
	 * 既存のモデル名が存在しない場合データベースへ追加し
	 * 存在する場合はタイムスタンプだけを変化させる関数
	 *
	 */
	public void InsertModelName(String modelname){

		Connection con = null;
		ResultSet result = null;
		System.out.println("InsertModelName");
		try{
			con = createConnection();

			Statement select = (Statement) con.createStatement();
			String sql = "SELECT name FROM modelname WHERE name ='" + modelname + "'";
			result = select.executeQuery(sql);
			double nowInMillis = System.currentTimeMillis()/1000;

			//既にモデル名が登録済みの場合はタイムスタンプのみ更新
			if(result == null || result.next()){
				String sqlupdate = "UPDATE modelname SET time = " + nowInMillis + " WHERE name = '" + modelname + "'";
				System.out.println("before update");
				Statement stmt = (Statement) con.createStatement();
				stmt.executeUpdate(sqlupdate);
			}else{ //モデル名が新規の場合はすべて更新
				String sql_ins = "INSERT INTO modelname VALUES(?,?)" ;
				PreparedStatement stmt = con.prepareStatement(sql_ins);
				stmt.setString(1, modelname);
				stmt.setDouble(2, nowInMillis);
			    stmt.executeUpdate();
			}
		}catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

}
