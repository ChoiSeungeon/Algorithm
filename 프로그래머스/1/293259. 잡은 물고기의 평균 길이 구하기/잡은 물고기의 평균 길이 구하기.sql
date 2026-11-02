-- 코드를 작성해주세요
select round(
    avg(
        case 
            when length is null or length < 10 then 10
            else length
        end
    ), 2) as AVERAGE_LENGTH
from fish_info;

