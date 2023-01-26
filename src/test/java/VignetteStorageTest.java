import hu.fenyvesvolgyimate.tollsystem.dao.EmergencyVignetteStorage;
import hu.fenyvesvolgyimate.tollsystem.dao.SqlLiteVignetteStorage;
import hu.fenyvesvolgyimate.tollsystem.dao.VignetteStorage;
import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VignetteStorageTest {
    @Test
    public void test(){
        VignetteStorage vignetteStorage = new EmergencyVignetteStorage();
        List<Vignette> vignetteList = vignetteStorage.findVignettesByRegistrationNumber("ABC-123");
        assertEquals(1 ,vignetteList.size());
    }
}
