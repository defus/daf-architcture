
package cm.gov.daf.sif.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p> Integration test using the jpa profile. 
 * @see AbstractProfessionServiceTests AbstractProfessionServiceTests for more details. </p>
 *
 * @author Albert
 */

@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpa")
public class TypeProfessionServiceJpaTests extends AbstractTypeProfessionServiceTests {

}