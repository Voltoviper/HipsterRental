<%@ include file="../../html/header/header_subsub.jsp"%>
<%@page import="wak.system.server.Login" %>
<%@ page import="wak.system.server.Seitenaufbau" %>



<ol class="breadcrumb">
  <li><a href="../../index.jsp">Home</a></li>
  <li><a href="./Uebersicht-Mitarbeiter.jsp">Mitarbeiterbereich</a></li>
  <li class="active">Neue Bestellung</li>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
  <div class="container">
    <div class="row details"><!-- start details -->
      <p class="h2">Neue Bestellung</p>
      <table style="width: 100%; valign:top" border="0">
        <tr>
          <!-- Hier der SEiteninhalt-->
          <form method="post" action="">
            <table style="width:100%">
              <tr>
                <td><p class="para"> Kunde</p></td><td>
                <select name="kundeid" size="1" style="width: 200px;">
                  <%Seitenaufbau.getKundenSelect(out);%></select>
              </td>
              </tr>
              <tr>
                <td>
                  <p class="para">
                  Artikel:</p>
                </td><td>
                <select name="produkte" size="5" multiple>
                  <%Seitenaufbau.getProdukteDatalist(out);%>
                </select>
              </td>
              </tr>
              <tr>
                <td>
                  <p class="para">Von:</p>
                </td><td>

                <input type="Text" name="von" id="von" ><img style="min-width: 10px; min-height: 10px;" src="../../img/calender/cal.gif" onclick="javascript:NewCssCal('von','ddMMyyyy','arrow', 'true', '24', '','future')" style="cursor:pointer"/>
              </td>
              </tr>
              <tr>
                <td><p class="para">Bis:</p></td>
                <td>
                  <input type="Text" name="bis" id="bis" ><img style="min-width: 10px; min-height: 10px;" src="../../img/calender/cal.gif" onclick="javascript:NewCssCal('bis','ddMMyyyy','arrow', 'true', '24','','future')" style="cursor:pointer"/>
                </td>
              </tr>
              <tr>
                <td><input type="submit" value="Eintragen"></td>
              </tr>
            </table>
          </form>

        </tr>
      </table>

    </div><!-- end  details -->


  </div>
</div>


<%@include file="../../html/footer/footer.jsp"%>

