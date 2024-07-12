package udemy.exer.exem1.controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CalculatorController {

    private  final AtomicLong counter = new AtomicLong();
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
    method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne")
            String numberOne,
            @PathVariable(value = "numberTwo")
            String numberTwo
    ) throws  Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value");
        }
        return converToDouble(numberOne) + converToDouble(numberTwo) ;
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double sub(
            @PathVariable(value = "numberOne")
            String numberOne,
            @PathVariable(value = "numberTwo")
            String numberTwo
    ) throws  Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value");
        }
        return converToDouble(numberOne) - converToDouble(numberTwo) ;
    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double mult(
            @PathVariable(value = "numberOne")
            String numberOne,
            @PathVariable(value = "numberTwo")
            String numberTwo
    ) throws  Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value");
        }
        return converToDouble(numberOne) * converToDouble(numberTwo) ;
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double dividir(
            @PathVariable(value = "numberOne")
            String numberOne,
            @PathVariable(value = "numberTwo")
            String numberTwo
    ) throws  Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value");
        }
        return converToDouble(numberOne) / converToDouble(numberTwo) ;
    }

    @RequestMapping(value = "/med/{numberOne}/{numberTwo}",
            method = RequestMethod.GET)
    public Double media(
            @PathVariable(value = "numberOne")
            String numberOne,
            @PathVariable(value = "numberTwo")
            String numberTwo
    ) throws  Exception{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedOperationException("Please set a numeric value");
        }

        Double resul = (converToDouble(numberOne) + converToDouble(numberTwo)) / 2;
        return  resul;
    }

    @RequestMapping(value = "/raiz/{numberOne}",
            method = RequestMethod.GET)
    public Double raiz(
            @PathVariable(value = "numberOne")
            String numberOne

    ) throws  Exception{
        if(!isNumeric(numberOne)){
            throw new UnsupportedOperationException("Please set a numeric value");
        }
        Double result = converToDouble(numberOne) * converToDouble(numberOne);
        return result;
    }




    private Double converToDouble(String strNumber) {
        if(strNumber == null){
            return 0D;
        }
        String number = strNumber.replaceAll(",", ".");
        if(isNumeric(number))return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null)return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9+]");
    }
}
