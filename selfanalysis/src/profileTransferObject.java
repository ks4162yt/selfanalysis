
public class profileTransferObject {
	
	String name;
	int appearance,chara,age,income,status,total;
	
	profileTransferObject(String name,int appearance,int chara,int age,int income,int status,int total){
		this.name = name;
		this.appearance = appearance;
		this.chara = chara;
		this.age = age;
		this.income = income;
		this.status = status;
		this.total = total;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAppearance() {
		return appearance;
	}
	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}
	public int getChara() {
		return chara;
	}
	public void setChara(int chara) {
		this.chara = chara;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
