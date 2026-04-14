package controlador;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLGenerator {

    public static void generarXML() {

//    	Esta clase XMLGenerator genera automáticamente un archivo XML siguiendo nuestro XSD.
//    	Primero se crea un documento vacío, luego se añade el elemento raíz y las tres ramas principales: users, creatures y configuration.
//    	Cada usuario y criatura se genera mediante funciones auxiliares que crean los nodos y atributos necesarios.
//    	Finalmente, se usa un Transformer para guardar el XML en un archivo llamado virtualCreatures.xml.
//    	Este XML es el que luego se arrastra a nuestra página web para mostrar las criaturas. 
    	
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //crea documento xml
            DocumentBuilder builder = factory.newDocumentBuilder(); //la herramienta que lo construye
            Document doc = builder.newDocument(); //este es el documento que se va a rellenar

            // raiz -----------------------------------------
            Element root = doc.createElement("virtualCreatures"); //se crea el elemento raiz con ese nombre
            doc.appendChild(root);

            // USERS --------------------------------------
            Element users = doc.createElement("users"); //esto crea lo de <users>
            root.appendChild(users);

            users.appendChild(createUser(doc, "Razer", "2009-04-02")); //aqui añadimos al usuario y el create user hace lo de: <user name="Razer" birthDate="2009-04-02"/>
            users.appendChild(createUser(doc, "Juan", "2007-09-11"));
            users.appendChild(createUser(doc, "Edurne", "2009-02-04"));

            // CREATURES --------------------------------------
            Element creatures = doc.createElement("creatures"); //lo mismo que lo de usuario
            root.appendChild(creatures);

            creatures.appendChild(createCreature(doc, 1, "Alissa", "Razer", "birthdayHat")); //pero aqui el metodo crea toda la estructura, el nombre el item, hapiness, etc
            creatures.appendChild(createCreature(doc, 2, "Sinclair", "Juan", "Sunglasses"));
            creatures.appendChild(createCreature(doc, 3, "Alicia", "Edurne", "Hamburger"));

            //  CONFIGURACION ---------------------------------------
            Element config = doc.createElement("configuration");
            config.setAttribute("difficulty", "medium"); //siempre tiene que ser medium porque esta puiesto asi en el xsd para cumplir con restricciones de LMS
            root.appendChild(config);

            Element gameCode = doc.createElement("gameCode");
            gameCode.setTextContent("CR-1234-ABC"); //el patron que mandan tener en el xsd de lms
            config.appendChild(gameCode);

            Element welcome = doc.createElement("welcomeMessage");
            welcome.setTextContent("Welcome to Virtual Creatures!");
            config.appendChild(welcome);

            Element max = doc.createElement("maxCreaturesPerUser");
            max.setTextContent("5");
            config.appendChild(max);

            // guardarlo --------------------------------------------------------
            Transformer transformer = TransformerFactory.newInstance().newTransformer(); //convierte el xml en texto, en un archivo con el nombre que le hemos puesto y .xml
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //con sanmgria

            transformer.transform(
                new DOMSource(doc),
                new StreamResult("virtualCreatures.xml")
            );

            System.out.println("XML generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Element createUser(Document doc, String name, String birth) { //añade los elementos obligatorios y devuelve el nodo para que el metodo principal lo añada a users
        Element user = doc.createElement("user");
        user.setAttribute("name", name);
        user.setAttribute("birthDate", birth);
        return user;
        //este metodo hace que eviotemos escribir las 3 lineas cada vez que añadamos usuarios
    }

    private static Element createCreature(Document doc, int id, String name, String owner, String itemName) {

        Element creature = doc.createElement("creature");
        creature.setAttribute("id", String.valueOf(id));
        creature.setAttribute("type", "default");
        creature.setAttribute("owner", owner);

        Element nameTag = doc.createElement("name");
        nameTag.setTextContent(name);
        creature.appendChild(nameTag);

        Element items = doc.createElement("items");
        creature.appendChild(items);

        Element item = doc.createElement("item");
        item.setAttribute("name", itemName);
        item.setAttribute("quantity", "1");
        items.appendChild(item);

        Element status = doc.createElement("status");
        status.appendChild(doc.createElement("happy"));
        creature.appendChild(status);

        creature.appendChild(simpleTag(doc, "experience", "0"));
        creature.appendChild(simpleTag(doc, "energy", "50"));
        creature.appendChild(simpleTag(doc, "hunger", "50"));
        creature.appendChild(simpleTag(doc, "happiness", "50"));

        Element photo = doc.createElement("photo");
        photo.setAttribute("path", "img/creature" + id + ".png");
        creature.appendChild(photo);

        return creature;
    }

    private static Element simpleTag(Document doc, String name, String value) { //este es para las etqietas simples como energyu 50
        Element e = doc.createElement(name);
        e.setTextContent(value);
        return e;
    }
}
