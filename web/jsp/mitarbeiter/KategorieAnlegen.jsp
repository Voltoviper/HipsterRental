<%@ include file="../../html/header/header_subsub.jsp"%>
<%@page import="wak.system.server.Login" %>
<%@ page import="wak.system.server.Seitenaufbau" %>
<ol class="breadcrumb">
  <li><a href="../../index.jsp">Home</a></li>
  <li><a href="./Uebersicht-Mitarbeiter.jsp">Mitarbeiterbereich</a></li>
  <li class="active">Kategorie Anlegen</li>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
  <div class="container">
    <div class="row details"><!-- start details -->
      <p class="h2">Neue Kategorie</p>
      <table style="width: 100%; valign:top" border="0">
        <tr>
          <form method="post" action="/KategorieAnlegen" enctype="multipart/form-data">
            <table>
              <tr>
                <td><p class="para">Kategoriename:</p></td>
                <td><input type="text" name = "name"></td>
              </tr>
              <tr>
                <td><p class="para"> Oberkategorie (optional)</p></td>
                <td>
                  <select name="oberkategorie" size="1" style="width: 200px;">
                    <option value=""></option>
                    <%Seitenaufbau.getKategorieOptionen(out);%>
                  </select>
                </td>

              </tr>
              <tr>
                <td><p class="para">Bild</p></td><td><input type="file" name="bild" accept="image/*"> </td>
              </tr>
              <tr>
                <td>

                </td>
                <td><input type="submit" value="Kategorie hinzuf&uuml;gen"></td>
              </tr>
            </table>
          </form>


        </tr>
      </table>

    </div><!-- end  details -->


  </div>
</div>


<%@include file="../../html/footer/footer.jsp"%>

