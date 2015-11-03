package test;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import test.wak.objects.BestellungTest;
import test.wak.system.db.DB_ConnectorTest;
import test.wak.system.db.DB_LoaderTest;
import test.wak.system.server.SeitenaufbauTest;
import wak.system.server.Seitenaufbau;

@RunWith(Suite.class)
@Suite.SuiteClasses({DB_ConnectorTest.class, SeitenaufbauTest.class, DB_LoaderTest.class, BestellungTest.class})
public class Testing {
    //nothing
}

