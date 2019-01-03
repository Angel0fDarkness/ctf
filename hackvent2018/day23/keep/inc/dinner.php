<?php

/*class SerialRequest{
    public $inject;
    function __construct(){
    }
    function __wakeup(){
        if(isset($this->inject)){
            eval($this->inject);
        }
    }
}*/
if(isset($_REQUEST['r'])){
    $var1=unserialize($_REQUEST['r'], FALSE);
    if(is_array($var1)){
        echo "Thanks for submitting dinner reservation: ".$var1[0]." - ".$var1[1] . "\n We will get back to you soon!";
    }
}
else{
    echo "Bad Request";
}

?>
