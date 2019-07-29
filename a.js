const express=require('express');
const bodyParser=require('body-parser');
const cors=require('cors');

let app=express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:false}));
app.use(cors());

let cars=[
    {id:1001,name:'Audi R8',color:'Red'},
    {id:1002,name:'BMW M5',color:'Blue'},
    {id:1003,name:'Mercedes S Class',color:'Yellow'},
    {id:1004,name:'Cadillac CTS',color:'Green'}
]

app.get('/cars',(req,res)=>{
    res.status(200).send(cars);
})

app.get('/cars/:id',(req,res)=>{
    let car=cars.find(a=>a.id==req.params.id);
    res.status(200).send(car);
})

app.post("/cars",(req,res)=>{
    let car={
        id:req.body.id,
        name:req.body.name,
        color:req.body.color
    }
    cars.push(car);
    res.status(200).send(cars);
})

app.put('/cars/:id',(req,res)=>{
    let index=cars.findIndex(a=>a.id==req.params.id);
    let newCar={
        id:req.body.id,
        name:req.body.name,
        color:req.body.color
    }
    cars.splice(index,1,newCar);
    res.status(200).send();
})

app.delete('/cars/:id',(req,res)=>{
    let index=cars.findIndex(a=>a.id==req.params.id);
    cars.splice(index,1);
    res.status(202).send();
});

const PORT=process.env.PORT||3000;
app.listen(PORT,()=>{
    console.log('Server running at '+PORT);
})