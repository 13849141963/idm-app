package com.fescotech.apps.idm.test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
 
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@ContextConfiguration(locations="classpath:com/fescotech/apps/idm/bootstrap/spring-context.xml")
public abstract class AbsRollbackTest {

}
