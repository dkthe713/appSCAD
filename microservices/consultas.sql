select u.usr_nick as user,u.usr_password as password,
 fd.fcdc_primer_nombre as primerNombre,fd.fcdc_segundo_nombre as segundoNombre,
 fd.fcdc_apellidos as apellidos,u.usr_identificacion as cedula
from usuario u
JOIN ficha_docente fd on u.fcdc_id=fd.fcdc_id
where u.usr_nick='wespana' and u.usr_password='12345';


select DISTINCT m.mtr_id,m.mtr_nombre
from horario h join materia m on h.mtr_id=m.mtr_id
where h.fcdc_id=1 

select DISTINCT fe.*
from ficha_estudiante fe
join horario_ficha_estudiante hfe on hfe.fces_id=fe.fces_id
join horario h on h.hrr_id=hfe.hrr_id
join ficha_docente fd on fd.fcdc_id=h.fcdc_id
join materia m on h.mtr_id=m.mtr_id
where fd.fcdc_id=1 and m.mtr_id=1102
order by fe.fces_apellidos;