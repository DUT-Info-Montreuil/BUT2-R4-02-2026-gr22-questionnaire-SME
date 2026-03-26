package universite_Paris8.iut.qdev.tp2026.gr22.mock;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import universite_Paris8.iut.qdev.tp2026.gr22.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr22.services.*;
import universite_Paris8.iut.qdev.tp2026.gr22.util.exception.*;
import java.io.File;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class QuestionnaireServiceTest {

    @Mock
    private IFileHandler fileHandler;

    @InjectMocks
    private QuestionnaireService service;

    @Test
    void testScenarioSucces() throws Exception {
        List<QuestionnaireDTO> mockList = List.of(new QuestionnaireDTO(1, "Quiz", null, null));
        when(fileHandler.extraireDonnees(any(File.class))).thenReturn(mockList);

        List<QuestionnaireDTO> result = service.chargerQuestionnaires(".", "pom.xml");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testScenario_NotFoundException() {
        assertThrows(NotFoundException.class, () -> {
            service.chargerQuestionnaires("/chemin/invalide", "test.json");
        });
    }

    @Test
    void testScenario_FichierNotExistException() {
        assertThrows(FichierNotExistException.class, () -> {
            service.chargerQuestionnaires(".", "fichier_fantome.json");
        });
    }

    @Test
    void testScenario_RecupFailException_ErreurParser() throws Exception {
        when(fileHandler.extraireDonnees(any(File.class))).thenThrow(new RuntimeException());

        assertThrows(RecupFailException.class, () -> {
            service.chargerQuestionnaires(".", "pom.xml");
        });
    }
}