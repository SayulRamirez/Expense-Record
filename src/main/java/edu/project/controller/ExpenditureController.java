package edu.project.controller;

import edu.project.domain.ExpenditureRequest;
import edu.project.domain.ExpenditureResponse;
import edu.project.domain.ExpenditureUpdate;
import edu.project.exceptions.DateValidationException;
import edu.project.exceptions.MonthInvalidException;
import edu.project.services.ExpenditureService;
import edu.project.services.ExpenditureServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Tag(name = "Controlador de gastos", description = "endpoints de los servicios para los gastos")
@RestController
@RequestMapping("/api/v1/expenditure")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    public ExpenditureController(ExpenditureServiceImpl expenditureService) {
        this.expenditureService = expenditureService;
    }

    @Operation(summary = "Registro de un nuevo gasto",
            responses = {
                    @ApiResponse(description = "si la categoria indicada no esta en el sistema", responseCode = "400"),
                    @ApiResponse(description = "Si el gasto se registro correctamente", responseCode = "201")
            }
    )
    @Transactional
    @PostMapping
    public ResponseEntity<ExpenditureResponse> registerExpenditure(@Valid @RequestBody ExpenditureRequest request) {

        LocalDate date = LocalDate.parse(request.date());

        ExpenditureResponse response = expenditureService.register(request, date);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Editar o actualizar un gasto",
            responses = {
                @ApiResponse(description = "si no se encontrarón gastos o categorias con los datos proporcionados", responseCode = "400"),
                    @ApiResponse(description = "Si la edición o actualización se realizó satisfactoriamente", responseCode = "200")
            }
    )
    @Transactional
    @PutMapping
    public ResponseEntity<ExpenditureResponse> editExpenditure(@Valid @RequestBody ExpenditureUpdate request) {

        LocalDate date = LocalDate.parse(request.date());

        ExpenditureResponse response = expenditureService.edit(request, date);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar todas los gastos",
            description = "Busca todos los gastos sin ningun parametro. Opcionalmente se puede agregar paginación a la consulta utilizando: ?page=2&size=4",
            responses = {
                    @ApiResponse(description = "Todas las coincidencias encontradas, si no se encontrarón será una lista vacía", responseCode = "200"),
                    @ApiResponse(description = "Si se formulo mal la url de la petición", responseCode = "400")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<Page<ExpenditureResponse>> getAllExpenditure(Pageable pageable) {

        return ResponseEntity.ok(expenditureService.findAll(pageable));
    }


    @Operation(summary = "Buscar todas los gastos por fecha especifica",
            description = "Busca todos los gastos por fecha especifica. Opcionalmente se puede agregar paginación a la consulta utilizando: ?page=2&size=4",
            parameters = @Parameter(name = "date", description = "fecha especifica en que se buscara con formato yyyy-MM-dd"),
            responses = {
                    @ApiResponse(description = "Todas las coincidencias encontradas, si no se encontrarón será una lista vacía", responseCode = "200"),
                    @ApiResponse(description = "Si se formulo mal la url de la petición", responseCode = "400")
            }
    )
    @GetMapping("/search/date={date}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureByDate(@PathVariable LocalDate date, Pageable pageable) {

        return ResponseEntity.ok(expenditureService.searchByDate(date, pageable));
    }

    @Operation(summary = "Buscar todas los gastos entre dos fechas",
            description = "Busca todos los gastos entre dos fechas, el primer parametro indica el inicio de la fecha y el segundo el final. Opcionalmente se puede agregar paginación a la consulta utilizando: ?page=2&size=4",
            parameters = {
                @Parameter(name = "startDate", description = "fecha de inicio de la busqueda en formato yyyy-MM-dd"),
                    @Parameter(name = "endDate", description = "fecha final de la busqueda en formato yyyy-MM-dd")
            },
            responses = {
                    @ApiResponse(description = "Todas las coincidencias encontradas, si no se encontrarón será una lista vacía", responseCode = "200"),
                    @ApiResponse(description = "Si se formulo mal la url de la petición", responseCode = "400")
            }
    )
    @GetMapping("/search/startDate={startDate}&endDate={endDate}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureBetweenTwoDate(
            @PathVariable LocalDate startDate, @PathVariable LocalDate endDate, Pageable pageable) {

        if (endDate.isBefore(startDate)) throw new DateValidationException("The end date: " + endDate + ", must be after the start date: " + startDate);

        return ResponseEntity.ok(expenditureService.searchBetweenTwoDate(startDate, endDate, pageable));
    }

    @Operation(summary = "Buscar todas los gastos por mes",
            description = "Busca todos los gastos por mes. Opcionalmente se puede agregar paginación a la consulta utilizando: ?page=2&size=4",
            parameters = @Parameter(name = "month", description = "Mes en que se buscara con formato yyyy-MM-dd"),
            responses = {
                    @ApiResponse(description = "Si el mes es incorrecto, es decir sea un número que no esta entre 1 y 12", responseCode = "400"),
                    @ApiResponse(description = "Todas las coincidencias encontradas, si no se encontrarón será una lista vacía", responseCode = "200"),
                    @ApiResponse(description = "Si se formulo mal la url de la petición", responseCode = "400")
            }
    )
    @GetMapping("/search/month={month}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureByMonth(@PathVariable Integer month, Pageable pageable) {

        if (month == null || month < 1 || month > 12) throw new MonthInvalidException("The month must be between 1 and 12");

        return ResponseEntity.ok(expenditureService.searchByMonth(month, pageable));
    }

    @Operation(summary = "Buscar todas los gastos por la categoria",
            description = "Busca todos los gastos por la categoria. Opcionalmente se puede agregar paginación a la consulta utilizando: ?page=2&size=4",
            parameters = @Parameter(name = "category", description = "Categoria de busqueda"),
            responses = {
                    @ApiResponse(description = "Si la categoria no existe o esta mal escrita", responseCode = "400"),
                    @ApiResponse(description = "Todas las coincidencias encontradas, si no se encontrarón será una lista vacía", responseCode = "200"),
            }
    )
    @GetMapping("/search/category={category}")
    public ResponseEntity<Page<ExpenditureResponse>> searchExpenditureByCategory(@PathVariable String category, Pageable pageable) {

        return ResponseEntity.ok(expenditureService.findByCategory(category, pageable));
    }

    @Operation(
            summary = "Elimina un gasto", parameters = @Parameter(name = "id", description = "identificador del gasto"),
            responses = @ApiResponse(description = "El gasto fue eliminado", responseCode = "404")
    )
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        expenditureService.delete(id);

        return ResponseEntity.notFound().build();
    }
}
