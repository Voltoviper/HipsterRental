<%@ include file="../../html/header/header_subsub.jsp"%>
<%@ page import="wak.system.server.Seitenaufbau" %>


<ol class="breadcrumb">
  <li><a href="../../index.jsp">Home</a></li>
  <li><a href="./Uebersicht-Mitarbeiter.jsp">Mitarbeiterbereich</a></li>
  <li class="active">Neues Produkt</li>
</ol>
<div class="main"><!-- start main -->
  <div class="container">
    <div class="row details"><!-- start details -->
      <p class="h2">Neues Produkt</p>
      <table style="width: 100%; valign:top" border="0">
        <tr>
          <td>
            <form method="post" action="/NeuesProdukt" enctype="multipart/form-data">
              <table style="width:100%; vertical-align: top;">
                <tr><td><p class="para"> Name: </p></td><td><input type="text" name="name"> </td><td><p class="para">Bezeichnung:</p> </td><td><textarea name="bezeichnung"></textarea></td></tr>
                <tr><td><p class="para">Hersteller: </p></td><td><input type="text" name="hersteller"> </td><td><p class="para">Beschreibung:</p> </td><td><textarea name="beschreibung"></textarea></td></tr>
                <tr><td><p class="para">Details:</p> </td><td><textarea name="details"></textarea></td><td><p class="para">Mietzins </p></td><td><input type="number" name="mietzins"></td></tr>
                <tr><td><p class="para">Kategorie: </p></td><td><%Seitenaufbau.getKategorieRadio(out);%></td><td><p class="para">Bild: </p></td><td><input type="file" name="bild"> </td></tr>
                <tr><td><input type="submit" value="Eintragen"></td></tr>
              </table>
            </form>

          </td>


        </tr>
      </table>

    </div><!-- end  details -->


  </div>
</div>


<%@include file="../../html/footer/footer.jsp"%>

