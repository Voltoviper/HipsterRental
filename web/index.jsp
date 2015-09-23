<%@ include file="./html/header/header.html"%>

<table style="width:100%">
  <tr>
    <td colspan="3">&nbsp;
    </td>
  </tr>
  <tr>
    <td class="login">&nbsp;</td>
    <td class="login_center">
      <form action="/home" method="post">
        <table>
          <tr>
            <td align="center">Benutzername:</td>
          </tr>
          <tr>
            <td align="center"><input type="text" name="user"/></td>
          </tr>
          <tr>
            <td align="center">Kennwort:</td>
          </tr>
          <tr>
            <td align="center"><input type="password" name="passwd"/></td>
          </tr>
          <tr><td align="center"><input type="submit" value="Login" name="Login"/><input type="submit" value="Registrieren" name="registrieren"/></td></tr>
        </table>
      </form></td>
    <td class="login">&nbsp;</td>
    </tr>
    </table>

 <%@include file="./html/footer/footer.html"%>
