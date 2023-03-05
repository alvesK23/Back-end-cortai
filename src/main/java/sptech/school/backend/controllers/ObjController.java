package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.entities.AddressEntity;
import sptech.school.backend.entities.BarberShopEntity;
import sptech.school.backend.obj.Barbearia;
import sptech.school.backend.entities.BarberShopEntity;
import sptech.school.backend.repositories.IAddressRepository;
import sptech.school.backend.repositories.IBarberShopRepository;
import sptech.school.backend.request.AddressRequest;
import sptech.school.backend.response.AddressResponse;
import sptech.school.backend.url.Download;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/objects")
public class ObjController {

    private final IBarberShopRepository rep;
    private final IAddressRepository rep2;
    public static void gravaRegistro(String registro, String nomeArq) {


        nomeArq += ".txt";

        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {


            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();

        }

        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {

            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }

    }

    public static void gravaArquivoTxt(List<BarberShopEntity> lista,
                                       List<AddressEntity> listaEndereco,
                                       String nomeArq) {

        int contaRegDados = 0;

        String header = "00";

        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        header += "01";

        gravaRegistro(header, nomeArq);

        String corpo;

        for (int i = 0; i < lista.size(); i++) {

            corpo = "02";
            corpo += String.format("%04d", lista.get(i).getId());
            corpo += String.format("%-50.50s", lista.get(i).getCompany());
            corpo += String.format("%-50.50s", lista.get(i).getCnpj());
            corpo += String.format("%-50.50s", lista.get(i).getEmail());
            corpo += String.format("%-50.50s", lista.get(i).getPhone());

            contaRegDados++;
            gravaRegistro(corpo, nomeArq);
        }

        for (int i = 0; i < listaEndereco.size(); i++) {

            corpo = "03";
            corpo += String.format("%04d", listaEndereco.get(i).getId());
            corpo += String.format("%-50.50s", listaEndereco.get(i).getState());
            corpo += String.format("%-50.50s", listaEndereco.get(i).getCity());
            corpo += String.format("%-50.50s", listaEndereco.get(i).getDistrict());
            corpo += String.format("%-50.50s", listaEndereco.get(i).getStreet());
            corpo += String.format("%-20.20s", listaEndereco.get(i).getZip());

            contaRegDados++;
            gravaRegistro(corpo, nomeArq);
        }

        String trailer = "01";

        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(trailer, nomeArq);
    }

    public void leArquivoTxt(String nomeArq) {

        BufferedReader entrada = null;
        String registro, tipoRegistro;

        nomeArq += ".txt";

        String company, cnpj, phone, email,state,city,district,street,zip;
        Long id,id2;

        Integer contaRegDadoLido = 0;
        Integer qtdRegDadoGravadoTrailer;



        try {

            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {

            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }

        try {

            registro = entrada.readLine(); // le o 1o registro

            while (registro != null) {

                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {

                    System.out.println(registro.substring(2, 23));

                } else if (tipoRegistro.equals("01")) {


                    qtdRegDadoGravadoTrailer = Integer.valueOf(registro.substring(2, 12));

                    if (contaRegDadoLido == qtdRegDadoGravadoTrailer) {

                        System.out.println("Quantidade de registros lidos compativel " +
                                "com quantidade de registros gravados");

                    } else {

                        System.out.println("Quantidade de registros lidos incompativel " +
                                "com quantidade de registros gravados");
                    }

                } else if (tipoRegistro.equals("02")) {


                    id = Long.valueOf(registro.substring(2, 6));
                    company = registro.substring(6, 56).trim();
                    cnpj = registro.substring(56, 106).trim();
                    phone = registro.substring(106, 156).trim();
                    email = registro.substring(156, 165).trim();
                    contaRegDadoLido++;



                    BarberShopEntity bar = new BarberShopEntity(company,cnpj,email, phone);


                    //No projeto de PI fazer um repository save

                   rep.save(bar);


                }else if (tipoRegistro.equals("03")) {


                    id2 = Long.valueOf(registro.substring(2, 6));
                    state = registro.substring(6, 56).trim();
                    city = registro.substring(56, 106).trim();
                    district = registro.substring(106, 156).trim();
                    street = registro.substring(156, 206).trim();
                    zip = registro.substring(206, 214).trim();
                    id = Long.valueOf(registro.substring(214, 215));
                    contaRegDadoLido++;



                    AddressEntity adr = new AddressEntity(state,city,district, street,zip,id);


                    //No projeto de PI fazer um repository save

                    rep2.save(adr);


                }else {

                    System.out.println("Tipo de registro inválido");
                }

                registro = entrada.readLine();
            }
        } catch (IOException erro) {

            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

    }



    @GetMapping("/write/{fileWriter}")
    public ResponseEntity<Void> fileWriter(@PathVariable String fileWriter) {

        List<AddressEntity> listaAdress = rep2.findAll();
        List<BarberShopEntity> listaGravada = rep.findAll();
        gravaArquivoTxt(listaGravada, listaAdress, fileWriter);

        return ResponseEntity.status(200).build();
    }


    @PostMapping("/read/{fileReader}")
    public ResponseEntity<Void> fileReader(@PathVariable String fileReader){

        leArquivoTxt(fileReader);
        return ResponseEntity.status(200).build();

    }


}
