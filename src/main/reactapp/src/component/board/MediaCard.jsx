import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import { LoginInfoContext } from '../Index';

export default function MediaCard(props) {

  const {loginInfo} = React.useContext(LoginInfoContext);

  const onDelete = (event, bno, mno)=>{
    axios.delete('/board/delete.do',{params:{bno:bno}})
    .then(response=>{
      window.location.href="/board"
    })
    .catch(error=>{console.log(error)})
  }

  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        sx={{ height: 140 }}
        image={"/uploadimg/"+props.board.gnameList[0]}
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
            {props.board.memail}
        </Typography>
        <Typography variant="body2" color="text.secondary">
            {props.board.bcontent}
        </Typography>
      </CardContent>
      <CardActions>
        {props.board.mno_fk==loginInfo.mno && <Button size="small" onClick={(event)=>{onDelete(event, props.board.bno, props.board.mno_fk)}}>Delete</Button>}
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}