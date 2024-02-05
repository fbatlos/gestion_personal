import kotlin.math.roundToInt

/**
 * Clase abierta que representa a una persona con un nombre y edad.
 * @property nombre Nombre de la persona.
 * @property edad Edad de la persona.
 */
open class Persona(open val nombre: String, edad: Int) {
    var edad: Int = edad
        private set

    /**
     * Incrementa la edad de la persona en uno y retorna un mensaje de cumpleaños.
     * @return Mensaje de cumpleaños.
     */
    fun celebrarCumple(): String {
        edad++
        return "Feliz cumpleaños $nombre!! Ahora tienes $edad años."
    }
    override fun toString(): String {
        return "Nombre = $nombre, Edad = $edad"
    }
}

/**
 * Clase abierta que hereda de Persona y representa a un empleado con salario y otros detalles.
 * @property salarioBase Salario base del empleado.
 * @property porcentajeImpuestos Porcentaje de impuestos aplicado al salario.
 */
open class Empleado(
    nombre: String,
    edad: Int,
    var salarioBase: Double,
    open var porcentajeImpuestos: Double = 10.0
) : Persona(nombre, edad) {

    var salarioNeto: Double = 0.00

    /**
     * Calcula el salario neto del empleado después de aplicar impuestos y retorna un mensaje.
     * @return Mensaje con el salario neto del empleado.
     */
    fun calculadoraSalario(): String {
        salarioNeto = salarioBase * (porcentajeImpuestos / 100)
        salarioNeto = salarioBase - salarioNeto
        return "Tu salario neto es: ${((salarioNeto) * 100.00).roundToInt() / 100.00}"
    }

    /**
     * Retorna un mensaje indicando que el empleado está trabajando.
     * @return Mensaje indicando que el empleado está trabajando.
     */
    fun trabajar(): String {
        return "$nombre está trabajando en la empresa."
    }

    override fun toString(): String {
        return "${super.toString()}, Salario: ${salarioNeto}€"
    }
}

/**
 * Clase que hereda de Empleado y representa a un gerente con un bonus y opciones adicionales.
 * @property bonus Monto del bonus para el gerente.
 * @property exentoImpuestos Indica si el gerente está exento de impuestos.
 */
class Gerente(
    var bonus: Double,
    var exentoImpuestos: Boolean = false,
    nombre: String,
    edad: Int,
    salarioBase: Double
) : Empleado(nombre, edad, salarioBase) {

    override var porcentajeImpuestos: Double = 33.99

    /**
     * Calcula el salario total del gerente, considerando el bonus y los impuestos, y retorna un mensaje.
     * @return Mensaje con el salario total del gerente.
     */
    fun calcularSalario(): String {
        if (exentoImpuestos == true) {
            return "Tu salario es: ${salarioBase + bonus}"
        }
        salarioNeto = salarioBase * (porcentajeImpuestos / 100)
        salarioNeto = salarioBase - salarioNeto
        return "Tu salario es: ${(((salarioNeto) * 100.00).roundToInt() / 100.00) + bonus}"
    }

    /**
     * Retorna un mensaje indicando que el gerente está administrando la empresa.
     * @return Mensaje indicando que el gerente está administrando la empresa.
     */
    fun administrar(): String {
        return "$nombre está administrando la empresa."
    }

    /**
     * Representación del gerente en forma de cadena, incluyendo el bonus y la exención de impuestos.
     * @return Mensaje al gerente.
     */
    override fun toString(): String {
        return "${super.toString()}, ha tenido un bonus de: $bonus y ${
            if (exentoImpuestos == true) {
                "si"
            } else {
                "no"
            }
        } está exento de impuestos"
    }
}

fun main() {
    // Crear una instancia de Persona
    val persona = Persona("pepe", 25)
    println(persona.celebrarCumple())
    println(persona.toString())

    // Crear una instancia de Empleado
    val empleado = Empleado("marta", 19, 19.9)
    println(empleado.calculadoraSalario())
    println(empleado.trabajar())
    println(empleado.toString())

    // Crear una instancia de Gerente
    val gerente = Gerente(12.00, false, "Mario", 24, 2000.00)
    println(gerente.administrar())
    println(gerente.calcularSalario())
    println(gerente.toString())
}
