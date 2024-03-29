import { useState } from "react";
import TemperatureInput from "./TemperatureInput";

function BoilingVerdict(props){

    if(props.celsius>=100){
        return(<><p>물이 끓습니다.</p></>)
    }

    return(<>
        <p>물이 끓지 않습니다.</p>
    </>)
}

function toCelsius(fahrenheit){
    return ((fahrenheit-32)*5)/9;
}

function toFahrenheit(celsius){
    return (celsius*9)/5+32;
}

function tryConvert(temperature, convert){
    const input = parseFloat(temperature);  console.log(input);
    if(Number.isNaN(input)){
        return "";
    }

    const output = convert(input); console.log(output);
    const rounded = Math.round(output*1000)/1000; console.log(rounded);
    return rounded.toString();
}

export default function Calculator(porps){
    const [temperature, setTemperature] = useState('');
    const [scale, setScale] = useState('c');

    const handleCelsiusChange = (temperature)=>{
        setTemperature(temperature);
        setScale('c');
    }
    const handleFahrenheitChange = (temperature)=>{
        setTemperature(temperature);
        setScale('f');
    }

    const celsius = scale === "f" ? tryConvert(temperature, toCelsius) : temperature;
    const fahrenheit = scale === "c" ? tryConvert(temperature, toFahrenheit) : temperature;

    return(<>
        <div>
            <TemperatureInput
                scale="c"
                temperature={celsius}
                onTemperatureChange={handleCelsiusChange}/>

            <TemperatureInput
                scale="f"
                temperature={fahrenheit}
                onTemperatureChange={handleFahrenheitChange}/>

            <BoilingVerdict celsius={parseFloat(celsius)}/>
        </div>
    </>)
}