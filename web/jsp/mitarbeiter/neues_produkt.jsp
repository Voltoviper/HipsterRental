<%@ include file="../../html/header/header_subsub.html"%>
<%@page import="wak.system.server.Login" %>
<%@ page import="wak.system.server.Seitenaufbau" %>
<table style="width:100%; valign:top; border-spacing: 0pt" border="0">
  <tr class="menu">
    <td colspan="3" class="menu"><% Seitenaufbau.getMenu(out, request.getCookies());%>
    </td>
  </tr>
  <tr>
    <td class="kat_nav">
      <table width="100%">
        <%Seitenaufbau.getMitarbeiterMenu(out, request.getCookies());%>
      </table>
    </td>
    <td class="main" valign="top">
      <table style="width: 100%; valign:top" border="0">
        <th style="text-align: center" colspan="3">Bestell&uumlbersicht</th>
        <tr>
          <td>
            <form method="post" action="/NeuesProdukt" enctype="multipart/form-data">
              <table style="width:100%; vertical-align: top;">
                <tr><td>Name: </td><td><input type="text" name="name"> </td><td>Bezeichnung: </td><td><textarea name="bezeichnung"></textarea></td></tr>
                <tr><td>Hersteller: </td><td><input type="text" name="hersteller"> </td><td>Beschreibung: </td><td><textarea name="beschreibung"></textarea></td></tr>
                <tr><td>Details: </td><td><textarea name="details"></textarea></td><td>Mietzins </td><td><input type="number" name="mietzins"></td></tr>
                <tr><td>Kategorie: </td><td><%Seitenaufbau.getKategorieRadio(out);%></td><td>Produktalternative (ProduktID): </td><td><input type="text" name="alternative"></td></tr>
                <tr><td>Bild: </td><td><input type="file" name="bild"> </td></tr>
                <tr><td><input type="submit" value="Eintragen"></td></tr>
              </table>
            </form>

          </td>
        </tr>
      </table>
    </td>
    <%Login.getLogin(out, request.getCookies());%>

  </tr>
</table>

<%@include file="../../html/footer/footer.html"%>

