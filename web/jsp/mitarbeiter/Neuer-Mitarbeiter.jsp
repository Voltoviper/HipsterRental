<%--
  Created by IntelliJ IDEA.
  User: Christoph Nebendahl
  Date: 28.10.2015
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../html/header/header_subsub.jsp" %>
<%@page import="wak.system.server.Login" %>
<%@ page import="wak.system.server.Seitenaufbau" %>
<ol class="breadcrumb">
  <li><a href="../../index.jsp">Home</a></li>
  <li><a href="./Uebersicht-Mitarbeiter.jsp">Mitarbeiterbereich</a></li>
  <li class="active">Neuer Mitarbeiter</li>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
  <div class="container">
    <div class="row details"><!-- start details -->
      <p class="h2">Neuer Mitarbeiter</p>
      <table style="width: 100%; valign:top" border="0">
        <tr>
          <form method="post" action="/NeuerMitarbeiter">
            <table style="width:100%;">
              <tr>
                <td><p class="para">Vorname</p></td><td><input type="text" name="vorname"></td><td><p class="para">Nachname</p></td><td><input type="text" name="nachname"></td>
              </tr>
              <tr>
                <td><p class="para">Benutzername</p></td><td><input type="text" name="benutzername"></td><td><p class="para">Kennwort</p></td><td><input type="password" name="password"></td>
              </tr>
              <tr>
                <td><input type="submit" name="eintragen" value="Anlegen"></td>
              </tr>
            </table>
          </form>


        </tr>
      </table>

    </div><!-- end  details -->


  </div>
</div>

<%@include file="../../html/footer/footer.jsp" %>
