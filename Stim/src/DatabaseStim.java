import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DatabaseStim {
	public static Connector con = new Connector();
	
	public static Vector<Games> getAllGames() throws SQLException{
		String query = "select * from game";
		ResultSet rs = con.executeQuery(query);
		
		Vector<Games> allGame = new Vector<Games> ();
		
		while(rs.next()) {
			allGame.add(new Games(rs.getString("gameid"), rs.getString("name"), rs.getInt("price"), rs.getString("genreId"), 
								rs.getInt("quantity")));
		}
		return allGame;
	}
	
	public static Games getGameWithId(String gameId) throws SQLException{
		String query = "select * from game where gameId =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, gameId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Games game = new Games(rs.getString("gameid"), rs.getString("name"), rs.getInt("price"), rs.getString("genreId"), 
				rs.getInt("quantity"));
		return game;
	}

	public static Vector<User> getAllUsers() throws SQLException{
		String query = "select * from user";
		ResultSet rs = con.executeQuery(query);
		
		Vector<User> allUser = new Vector<User> ();
		
		while(rs.next()) {
			allUser.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("gender"), rs.getString("country"), 
								rs.getString("role"), rs.getInt("userId")));
		}
		return allUser;
	}
	
	public static Vector<Transaction> getAllTransaction(int userId) throws SQLException{
		String query = "select * from transaction where userId =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, userId);
		ResultSet rs = ps.executeQuery();
		
		Vector<Transaction> allTransactions = new Vector<Transaction> ();
		
		while(rs.next()) {
			allTransactions.add(new Transaction(rs.getString("gameId"), rs.getInt("transactionId"),rs.getInt("userId"), 
												rs.getInt("gameQuantity")));
		}
		return allTransactions;
	}
	
	public static Vector<Genre> getAllGenre() throws SQLException{
		String query = "select * from genre";
		ResultSet rs = con.executeQuery(query);
		
		Vector<Genre> allGenre = new Vector<Genre> ();
		
		while(rs.next()) {
			allGenre.add(new Genre(rs.getString("genreId"), rs.getString("genreName")));
		}
		return allGenre;
	}
	
	public static String getGenreWithID(String genreID) throws SQLException{
		String query = "select * from genre where genreID = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, genreID);
		ResultSet rs = ps.executeQuery();

		rs.next();
		String genre =  rs.getString("genreName");
		
		return genre;
	}
	
	public static int getUserID(String username)throws SQLException{
		String query = "select userid from user where username = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		int userID = rs.getInt(1);
		
		return userID;
	}
	
	public static void addUser(User user)throws SQLException{
		String query = "INSERT INTO user(username, password, gender, country, role) VALUES (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getGender());
		ps.setString(4, user.getCountry());
		ps.setString(5, user.getRole());
		ps.executeUpdate();
	}
	
	public static void addTransaction(Transaction transaction)throws SQLException{
		String query = "INSERT INTO transaction(userId,gameId,gameQuantity) VALUES (?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, transaction.getUserId());
		ps.setString(2, transaction.getGameId());
		ps.setInt(3, transaction.getGameQuantity());
		ps.executeUpdate();
	}
	
	public static void addGames(Games games)throws SQLException{
		String query = "INSERT INTO game(gameId, name, price, genreId, quantity) VALUES (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, games.getId());
		ps.setString(2, games.getName());
		ps.setInt(3, games.getPrice());
		ps.setString(4, games.getGenre());
		ps.setInt(5, games.getQuantity());
		ps.executeUpdate();
	}
	
	public static void addGenre(Genre genre)throws SQLException{
		String query = "INSERT INTO genre(genreId, genreName) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, genre.getGenreId());
		ps.setString(2, genre.getGenreName());
		ps.executeUpdate();
	}
	
	public static void deleteGenre(Genre genre)throws SQLException{
		String query = "DELETE FROM genre WHERE genreId =?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, genre.getGenreId());
		ps.executeUpdate();
	}
	
	public static void deleteGame(Games games)throws SQLException{
		String query = "DELETE FROM game WHERE gameId = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, games.getId());
		ps.executeUpdate();
	}
	
	public static void updateGame(Games games)throws SQLException{
		String query = "UPDATE game SET gameId = ?, name=? ,price= ?,genreId= ?,quantity= ? WHERE gameId = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, games.getId());
		ps.setString(2, games.getName());
		ps.setInt(3, games.getPrice());
		ps.setString(4, games.getGenre());
		ps.setInt(5, games.getQuantity());
		ps.setString(6, games.getId());
		ps.executeUpdate();
	}
	
	public static void updateGenre(Genre genre)throws SQLException{
		String query = "UPDATE genre SET genreId=?, genreName=? WHERE genreId=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, genre.getGenreId());
		ps.setString(2, genre.getGenreName());
		ps.setString(3, genre.getGenreId());
		ps.executeUpdate();
	}

	public static void updateTransaction(Transaction transaction)throws SQLException{
		String query = "UPDATE transaction SET transactionId=? , userId=?, gameId=? , gameQuantity= ? WHERE transactionId=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, transaction.getTransactionId());
		ps.setInt(2, transaction.getUserId());
		ps.setString(3, transaction.getGameId());
		ps.setInt(4, transaction.getGameQuantity());
		ps.setInt(5, transaction.getTransactionId());
		ps.executeUpdate();
	}
	
}
