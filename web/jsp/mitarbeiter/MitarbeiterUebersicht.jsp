<%@ include file="../../html/header/header_subsub.jsp"%>
<%@ page import="wak.system.server.Seitenaufbau" %>

<ol class="breadcrumb">
  <li><a href="../../index.jsp">Home</a></li>
  <li><a href="./Uebersicht-Mitarbeiter.jsp">Mitarbeiterbereich</a></li>
  <li class="active">Mitarbeiterliste</li>
</ol>
</div>
</div>
<div class="main"><!-- start main -->
  <div class="container">
    <div class="row details"><!-- start details -->
      <p class="h2">Mitarbeiterliste</p>
      <table style="width: 100%; valign:top" border="0">
        <tr>
          <%Seitenaufbau.getMitarbeiterUebersicht(out);%>


        </tr>
      </table>

    </div><!-- end  details -->


  </div>
</div>

<%@include file="../../html/footer/footer.jsp"%>

