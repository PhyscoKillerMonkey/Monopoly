package monopoly;

public class Card {

  private String type;
  private String content;
  private String action;
  
  public Card(String type, String content, String action) {
    this.type = type;
    this.content = content;
    this.action = action;
  }
  
  public void action() {
    
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    if (type.equals("chance") || type.equals("communityChest")) {
      this.type = type;
    } else {
      try {
        throw(new Throwable("Invalid card type"));
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
