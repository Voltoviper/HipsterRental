package test;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import test.wak.system.db.DB_ConnectorTest;
import test.wak.system.server.SeitenaufbauTest;
import wak.system.server.Seitenaufbau;

@RunWith(Suite.class)
@Suite.SuiteClasses({DB_ConnectorTest.class, SeitenaufbauTest.class})
public class Testing {
    //nothing
}

