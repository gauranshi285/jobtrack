package com.jobtrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class JobController {
  @Autowired JobRepository repo;
  @GetMapping("/api/jobs") public List<Job> all(){return repo.findAll();}
  @PostMapping("/api/jobs") public Job add(@RequestBody Job j){return repo.save(j);}
  @DeleteMapping("/api/jobs/{id}") public void del(@PathVariable Long id){repo.deleteById(id);}
  
  @GetMapping("/")
  public String home(){
    return """
    <html><head><title>JobTrack - Intermediate</title>
    <style>body{font-family:Arial;max-width:700px;margin:40px auto;padding:20px} .job{border:1px solid #ddd;padding:12px;margin:8px 0;border-radius:8px} input{padding:8px;margin:4px} button{padding:8px 16px;background:#000;color:#fff;border:0;border-radius:6px;cursor:pointer}</style>
    </head><body>
    <h1>?? JobTrack v3 - Database Connected</h1>
    <p>Intermediate Project | Spring Boot + H2 Database + Deployable</p>
    <div><input id='c' placeholder='Company'><input id='r' placeholder='Role'><input id='s' placeholder='Status'><button onclick='add()'>Add Job</button></div>
    <div id='list'></div>
    <script>
    async function load(){let res=await fetch('/api/jobs');let jobs=await res.json();document.getElementById('list').innerHTML=jobs.map(j=><div class='job'><b></b> |  |  <button onclick='del()' style='float:right'>Delete</button></div>).join('');}
    async function add(){let c=document.getElementById('c').value,r=document.getElementById('r').value,s=document.getElementById('s').value;if(!c||!r)return alert('Fill all');await fetch('/api/jobs',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({company:c,role:r,status:s})});document.getElementById('c').value='';load();}
    async function del(id){await fetch('/api/jobs/'+id,{method:'DELETE'});load();}
    load();
    </script></body></html>
    """;
  }
}
