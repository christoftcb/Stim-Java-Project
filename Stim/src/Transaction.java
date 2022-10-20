
public class Transaction {
	private String gameId;
	private int transactionId, userId, gameQuantity;
	
	public Transaction(String gameId, int transactionId, int userId, int gameQuantity) {
		this.gameId = gameId;
		this.transactionId = transactionId;
		this.userId = userId;
		this.gameQuantity = gameQuantity;
	}
	
	public String getGameId() {
		return gameId;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getGameQuantity() {
		return gameQuantity;
	}
	
	public void setGameQuantity(int gameQuantity) {
		this.gameQuantity = gameQuantity;
	}
	
}
