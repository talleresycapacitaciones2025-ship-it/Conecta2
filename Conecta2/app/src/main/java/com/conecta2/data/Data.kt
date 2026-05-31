package com.conecta2.data

enum class UserProfile {
    ADULT,
    TEEN
}

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Learn : Screen("learn")
    object Games : Screen("games")
    object Settings : Screen("settings")
    object FingerprintGame : Screen("fingerprint_game")
    object MatchGame : Screen("match_game")
    object PasswordGenerator : Screen("password_generator")
    object Agreement : Screen("agreement")
}

data class MatchItem(
    val id: Int,
    val term: String,
    val definition: String
)

data class LearnCard(
    val id: Int,
    val title: String,
    val content: String,
    val advice: String,
    val category: CardCategory
)

enum class CardCategory {
    DANGERS,
    PROTECTION,
    FAMILY
}

data class ServiceData(
    val name: String,
    val dataExposed: List<String>
)

val servicesList = listOf(
    ServiceData("Instagram", listOf("Fotos personales", "Ubicación", "Contactos", "Gustos e intereses")),
    ServiceData("TikTok", listOf("Videos personales", "Ubicación", "Contactos", "Tiempo de uso")),
    ServiceData("WhatsApp", listOf("Mensajes", "Contactos", "Estado", "Ubicación en tiempo real")),
    ServiceData("Facebook", listOf("Información personal", "Fotos", "Amigos", "Actividad")),
    ServiceData("Twitter/X", listOf("Opiniones", "Ubicación", "Seguidores", "Intereses")),
    ServiceData("Snapchat", listOf("Fotos efímeras", "Ubicación", "Contactos", "Tiempo de uso")),
    ServiceData("Twitch", listOf("Tiempo de visualización", "Donaciones", "Chat", "Seguidores")),
    ServiceData("Telegram", listOf("Mensajes", "Grupos", "Contactos", "Archivos compartidos")),
    ServiceData("LinkedIn", listOf("Experiencia laboral", "Educación", "Contactos profesionales", "Habilidades")),
    ServiceData("YouTube", listOf("Historial de búsqueda", "Videos vistos", "Suscripciones", "Gustos")),
    ServiceData("Spotify", listOf("Gustos musicales", "Playlists", "Artistas favoritos", "Tiempo de escucha")),
    ServiceData("Amazon", listOf("Compras", "Dirección", "Tarjetas", "Preferencias")),
    ServiceData("MAX", listOf("Series vistas", "Preferencias", "Tiempo de visualización", "Perfil")),
    ServiceData("Netflix", listOf("Series vistas", "Preferencias", "Tiempo de visualización", "Perfil")),
    ServiceData("Disney+", listOf("Series vistas", "Preferencias", "Perfiles familiares", "Tiempo de uso")),
    ServiceData("Temu", listOf("Compras", "Dirección", "Búsquedas", "Preferencias")),
    ServiceData("Roblox", listOf("Juegos jugados", "Amigos", "Chat", "Compras virtuales")),
    ServiceData("Juegos en línea", listOf("Tiempo de juego", "Logros", "Amigos virtuales", "Compras")),
    ServiceData("Google", listOf("Búsquedas", "Ubicación", "Correo", "Documentos", "Fotos"))
)

val matchQuestionsPool = listOf(
    MatchItem(1, "Phishing", "Intento de robar información personal mediante engaño"),
    MatchItem(2, "Contraseña fuerte", "Combinación de letras, números y símbolos difíciles de adivinar"),
    MatchItem(3, "Huella digital", "Rastro de información que dejamos en internet"),
    MatchItem(4, "Privacidad", "Control sobre quién puede ver tu información"),
    MatchItem(5, "Malware", "Software malicioso que daña dispositivos"),
    MatchItem(6, "Ciberacoso", "Acoso o intimidación a través de medios digitales"),
    MatchItem(7, "Doble factor", "Capa extra de seguridad con dos métodos de verificación"),
    MatchItem(8, "Navegación segura", "Usar HTTPS y sitios verificados"),
    MatchItem(9, "Red privada", "Conexión protegida con contraseña"),
    MatchItem(10, "Datos personales", "Información que te identifica como nombre, dirección, etc."),
    MatchItem(11, "Cookie", "Pequeño archivo que guarda preferencias del sitio"),
    MatchItem(12, "Firewall", "Barrera de seguridad entre tu dispositivo e internet"),
    MatchItem(13, "VPN", "Red que cifra tu conexión para mayor privacidad"),
    MatchItem(14, "Backup", "Copia de seguridad de tus datos importantes"),
    MatchItem(15, "Actualización", "Mejora de software que corrige vulnerabilidades"),
    MatchItem(16, "Suplantación", "Hacerse pasar por otra persona en línea"),
    MatchItem(17, "Geolocalización", "Compartir tu ubicación en tiempo real"),
    MatchItem(18, "Contenido viral", "Información que se comparte masivamente"),
    MatchItem(19, "Configuración privada", "Limitar quién ve tu contenido"),
    MatchItem(20, "Denuncia", "Reportar contenido o comportamiento inapropiado"),
    MatchItem(21, "Sexting", "Enviar contenido íntimo por medios digitales"),
    MatchItem(22, "Grooming", "Adulto que gana confianza de menor con fines indebidos"),
    MatchItem(23, "Fake news", "Noticias falsas creadas para engañar"),
    MatchItem(24, "Adicción digital", "Uso excesivo de tecnología que afecta la vida diaria"),
    MatchItem(25, "Padres digitales", "Adultos que guían el uso seguro de internet"),
    MatchItem(26, "Cyberbullying", "Acoso entre pares mediante tecnología"),
    MatchItem(27, "Identidad digital", "Cómo te presentas y eres percibido en línea"),
    MatchItem(28, "Reputación online", "Imagen que otros tienen de ti en internet"),
    MatchItem(29, "Oversharing", "Compartir demasiada información personal"),
    MatchItem(30, "Clickbait", "Contenido sensacionalista para generar clics"),
    MatchItem(31, "Enlace seguro", "URL que comienza con https://"),
    MatchItem(32, "Antivirus", "Programa que detecta y elimina malware"),
    MatchItem(33, "Nube", "Almacenamiento remoto de archivos"),
    MatchItem(34, "Contraseña maestra", "Contraseña principal para gestor de contraseñas"),
    MatchItem(35, "Verificación", "Confirmar que algo es auténtico"),
    MatchItem(36, "Bloqueo", "Impedir que alguien te contacte"),
    MatchItem(37, "Reporte", "Notificar contenido inapropiado a la plataforma"),
    MatchItem(38, "Permiso", "Autorización para acceder a datos del dispositivo"),
    MatchItem(39, "Anonimato", "Navegar sin revelar identidad"),
    MatchItem(40, "Rastreo", "Seguimiento de actividad en línea"),
    MatchItem(41, "Perfil público", "Información visible para todos"),
    MatchItem(42, "Cifrado", "Codificar datos para protegerlos"),
    MatchItem(43, "Token", "Código temporal de seguridad"),
    MatchItem(44, "Sesión activa", "Conexión abierta a una cuenta"),
    MatchItem(45, "Cerrar sesión", "Finalizar conexión a una cuenta"),
    MatchItem(46, "Recuperación", "Proceso para restablecer acceso"),
    MatchItem(47, "Pregunta secreta", "Pregunta de seguridad para recuperación"),
    MatchItem(48, "Dispositivo confiable", "Equipo autorizado para acceso"),
    MatchItem(49, "Alerta temprana", "Notificación de actividad sospechosa"),
    MatchItem(50, "Educación digital", "Aprender a usar internet responsablemente")
)

val learnCards = listOf(
    // Peligros en internet (5 tarjetas)
    LearnCard(1, "Ciberacoso", "El ciberacoso es el uso de medios digitales para intimidar, amenazar o humillar a alguien. Puede ocurrir en redes sociales, mensajes o juegos en línea.", "Si experimentas o ves ciberacoso, no respondas, guarda pruebas y habla con un adulto de confianza.", "Qué hacer: Bloquea al acosador, reporta el contenido y busca apoyo."),
    LearnCard(2, "Phishing", "El phishing intenta engañarte para que reveles información personal haciéndose pasar por una entidad confiable mediante correos o mensajes falsos.", "Nunca hagas clic en enlaces sospechosos. Verifica siempre el remitente y la URL.", "Qué hacer: Elimina el mensaje y reporta el intento de phishing."),
    LearnCard(3, "Oversharing", "Compartir demasiada información personal puede poner en riesgo tu privacidad y seguridad. Incluye fotos, ubicación, escuela, rutinas.", "Piensa antes de publicar: ¿esta información podría usarse en mi contra?", "Qué hacer: Revisa y limita lo que compartes en redes sociales."),
    LearnCard(4, "Contenido inapropiado", "Internet contiene contenido violento, sexual o dañino que puede afectar tu bienestar emocional.", "Si ves algo que te incomoda, cierra la página y habla con un adulto.", "Qué hacer: Usa filtros de contenido y reporta material inapropiado."),
    LearnCard(5, "Desinformación", "Las fake news son noticias falsas diseñadas para engañar. Pueden propagarse rápidamente en redes sociales.", "Verifica la información en múltiples fuentes confiables antes de compartir.", "Qué hacer: No compartas contenido sin verificar su autenticidad."),
    
    // Cómo proteger tu huella (5 tarjetas)
    LearnCard(6, "Contraseñas fuertes", "Una contraseña fuerte tiene al menos 12 caracteres con mayúsculas, minúsculas, números y símbolos. Evita datos personales.", "Usa un gestor de contraseñas y activa la verificación en dos pasos.", "Qué hacer: Cambia contraseñas cada 3-6 meses y nunca las repitas."),
    LearnCard(7, "Privacidad en redes", "Configura tus perfiles como privados para controlar quién ve tu contenido. Revisa regularmente la configuración.", "Solo acepta solicitudes de personas que conoces en la vida real.", "Qué hacer: Revisa quién puede ver tus publicaciones y etiqueta."),
    LearnCard(8, "Navegación segura", "Usa solo sitios con HTTPS (candado verde). Evita redes WiFi públicas para actividades sensibles.", "Mantén actualizado tu navegador y sistema operativo.", "Qué hacer: Usa VPN en redes públicas y cierra sesiones al terminar."),
    LearnCard(9, "Protección de datos", "No compartas información personal como dirección, teléfono, escuela o documentos con desconocidos.", "Lee las políticas de privacidad antes de aceptar permisos.", "Qué hacer: Minimiza los datos que proporcionas en formularios."),
    LearnCard(10, "Dispositivos seguros", "Protege tus dispositivos con PIN, patrón o biometría. Instala antivirus y mantén todo actualizado.", "No descargues apps de fuentes no oficiales.", "Qué hacer: Activa el bloqueo automático y el cifrado del dispositivo."),
    
    // Consejos para familias (5 tarjetas)
    LearnCard(11, "Diálogo abierto", "Mantén comunicación constante sobre el uso de internet. Crea un ambiente de confianza donde puedan compartir preocupaciones.", "Establece momentos para hablar sobre experiencias en línea sin juzgar.", "Qué hacer: Pregunta sobre sus apps favoritas y úsalas juntos."),
    LearnCard(12, "Reglas claras", "Establece límites de tiempo, horarios y lugares para usar dispositivos. Crea un convenio familiar digital.", "Las reglas deben ser consistentes y aplicarse a todos los miembros.", "Qué hacer: Define zonas libres de pantallas como dormitorios."),
    LearnCard(13, "Ejemplo positivo", "Los adultos deben modelar el comportamiento digital que esperan. Usa tecnología de forma responsable.", "Demuestra equilibrio entre vida digital y presencial.", "Qué hacer: Practica lo que predicas sobre tiempo de pantalla."),
    LearnCard(14, "Educación continua", "Aprende junto a tus hijos sobre nuevas apps, tendencias y riesgos. La tecnología evoluciona constantemente.", "Mantente informado sobre plataformas que usan tus hijos.", "Qué hacer: Investiga together sobre configuraciones de seguridad."),
    LearnCard(15, "Herramientas parentales", "Usa controles parentales apropiados para la edad, pero no como único método de protección.", "Combina herramientas técnicas con educación y diálogo.", "Qué hacer: Configura filtros de contenido y límites de tiempo.")
)
