package gdg.androidtitlan.androidchatmaterialdesign.login;

/**
 * 14/05/16.
 */
public class UserCredential {

  private String mail;
  private String password;

  public UserCredential(String mail, String password) {
    this.mail = mail;
    this.password = password;
  }

  public String getMail() {
    return mail;
  }

  public String getPassword() {
    return password;
  }
}
