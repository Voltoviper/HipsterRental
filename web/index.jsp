<%@ include file="html/header/header.jsp" %>
<%@ page import="wak.system.server.Seitenaufbau" %>

<ol class="breadcrumb">
  <li class="active"><a href="../../index.jsp">Home</a></li>

</ol>
</div>
</div>
<div class="main"><!-- start main -->
  <div class="container">


    <div class="row details"><!-- start details -->
      <p class="h2">Unsere Highlights</p>
      <table>
      <%Seitenaufbau.getEmpfehlungen(out);%>
      </table>

    </div><!-- end  details -->


  </div>
</div>
<%@include file="html/footer/footer.jsp"%>