<%@ include file="../../html/header/header_subsub.jsp"%>
<%@ page import="wak.system.server.Seitenaufbau" %>


<ol class="breadcrumb">
  <li><a href="../../index.jsp">Home</a></li>
  <li><a href="./Uebersicht-Mitarbeiter.jsp">Mitarbeiterbereich</a></li>
  <li class="active">Neues Gerät</li>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
  <div class="container">
    <div class="row details"><!-- start details -->
      <p class="h2">Neues Gerät hinzufügen</p>
      <table style="width: 100%; valign:top" border="0">
        <tr>
          <form method="post" action="/GeraetEinfuegen">
            <table>
              <tr>
                <td><p class="para">Produktauswahl</p></td>
                <td><select name="produkt" size="1" style="width: 200px;">
                  <%Seitenaufbau.getProdukteDatalist(out);%>
                </select>

                </td>
              </tr>
              <tr>
                <td>

                </td>
                <td><input type="submit" value="Gerält hinzufügen"></td>
              </tr>
            </table>
          </form>


        </tr>
      </table>

    </div><!-- end  details -->


  </div>
</div>

<%@include file="../../html/footer/footer.jsp"%>

