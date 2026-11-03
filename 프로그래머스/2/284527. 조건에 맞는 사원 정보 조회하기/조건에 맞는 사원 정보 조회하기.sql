-- 코드를 작성해주세요
select sum(score) as SCORE, e.emp_no, emp_name, position, email
from hr_employees e
join hr_grade g
on e.emp_no = g.emp_no
where year = 2022
group by e.emp_no
order by SCORE desc
limit 1;